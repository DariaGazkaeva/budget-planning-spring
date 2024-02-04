document.addEventListener('DOMContentLoaded', () => {
    const createCategoryWidget = document.querySelector('.create-category-widget');
    const createCategoryForm = createCategoryWidget.querySelector("form");
    const createCategoryWidgetButton = document.querySelector(".create-category-widget__button");
    const categorySelect = document.querySelector("select.category-select");
    const deleteCategoryButton = document.querySelector(".delete-category-widget__button");
    const deleteCategoryWidget = document.querySelector(".delete-category-widget");
    let deleteCategoryWidgetOffers = deleteCategoryWidget.querySelectorAll(".delete-category-widget__a");

    createCategoryWidgetButton.addEventListener('click', () => {
        createCategoryWidget.classList.toggle("display-none");
    });

    deleteCategoryButton.addEventListener('click', () => {
        deleteCategoryWidget.classList.toggle('display-none');
    });

    for (let i = 0; i < deleteCategoryWidgetOffers.length; i++) {
        deleteCategoryWidgetOffers[i]
            .addEventListener('click', (event) => onClickDelete(event, deleteCategoryWidgetOffers[i]));
    }

    const makeHeaders = () => {
        return {
            'Content-Type': 'application/x-www-form-urlencoded',
            'Accept': 'application/json'
        };
    };

    createCategoryForm.addEventListener("submit", (event) => {
        event.preventDefault();
        let incomeSelect = createCategoryForm.querySelector("select.income");
        let income = incomeSelect.options[incomeSelect.selectedIndex].value;
        let name = createCategoryForm.querySelector("input.category-name").value;
        const authHeaderName = document.querySelector('meta[name=_csrf_header]').content;
        const authToken = document.querySelector('meta[name=_csrf]').content;
        const headers = makeHeaders();
        headers[authHeaderName] = authToken;
        let request = `income=${income}&name=${name}`;

        fetch(createCategoryForm.action, {
            method: "POST",
            headers: headers,
            body: request
        }).then(async response => {
            if (response.ok) {
                let data = await response.json();
                if ('income=' + data.income === window.location.search.substring(1)) {
                    let option = `<option value="${data.id}">${data.name}</option>`;
                    categorySelect.insertAdjacentHTML('beforeend', option);
                    let li = `<li><a class="delete-category-widget__a" data-category-id="${data.id}" href="/category/${data.id}">${data.name}</a></li>`;
                    const ul = deleteCategoryWidget.querySelector("ul");
                    ul.insertAdjacentHTML('beforeend', li);
                    ul.lastChild.addEventListener('click', (event) => onClickDelete(event, event.target));
                    deleteCategoryWidgetOffers = deleteCategoryWidget.querySelectorAll(".delete-category-widget__a");
                }
            } else if (response.status === 403) {
                alert('FORBIDDEN OPERATION');
            } else if (response.status === 400) {
                alert('BAD REQUEST');
            } else {
                alert('SERVER ERROR');
            }
        }).catch(error => {
            console.log(error);
            alert('UNKNOWN ERROR')
        });
    })

    const onClickDelete = (event, category) => {
        event.preventDefault();
        let categoryId = category.getAttribute("data-category-id");
        const authHeaderName = document.querySelector('meta[name=_csrf_header]').content;
        const authToken = document.querySelector('meta[name=_csrf]').content;
        const headers = makeHeaders();
        headers[authHeaderName] = authToken;

        fetch(category.href, {
            method: "DELETE",
            headers: headers
        }).then(async response => {
            if (response.ok) {
                let option = categorySelect.querySelector(`option[value="${categoryId}"]`);
                category.remove();
                categorySelect.removeChild(option);
            } else if (response.status === 403) {
                alert('FORBIDDEN OPERATION');
            } else if (response.status === 400) {
                alert('BAD REQUEST');
            } else {
                alert('SERVER ERROR');
            }
        }).catch(error => {
            console.log(error);
            alert('UNKNOWN ERROR')
        });

    }
})
