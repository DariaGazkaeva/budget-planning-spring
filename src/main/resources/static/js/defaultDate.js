function getFirstDayOfMonth(year, month) {
    return new Date(year, month, 1, 13);
}
const today = new Date();
today.setHours(13)

const firstDay = getFirstDayOfMonth(
    today.getFullYear(),
    today.getMonth(),
);

document.addEventListener('DOMContentLoaded', () => {
    const field = document.querySelector(".date-money-operation");
    if (field.value === '') field.valueAsDate = today;
})

document.addEventListener('DOMContentLoaded', () => {
    const field = document.querySelector(".first-date-money-operation");
    if (field.value === '') field.valueAsDate = firstDay;
})
