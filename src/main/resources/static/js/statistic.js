const resp = fetch("/profile/statistics/list", {
    method: "GET"
})
    .then(response => response.json())
    .then(resp => {
        const income = resp["income"];
        const expense = resp["expense"];
        new Morris.Donut({
            element: 'income',
            data: income,
            formatter: function (y) { return y}
        });
        new Morris.Donut({
            element: 'expense',
            data: expense,
            formatter: function (y) { return y }
        });
    }).catch(() => {
        alert('Server error')
    })
