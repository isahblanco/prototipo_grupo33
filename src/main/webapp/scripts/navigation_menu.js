const pageName = document.title;
const navigationMenu = document.querySelectorAll(".upper-menu .links a");

(function updateMenu() {
    if (pageName === "Fantasty - Home") selectMenuItem(0);
    else if (pageName === "Fantasty - Search") selectMenuItem(1);
    else if (pageName === "Fantasty - Recipe Creation") selectMenuItem(2);
    else if (pageName === "Fantasty - Book") selectMenuItem(3);
    
    function selectMenuItem(index) {
        navigationMenu.forEach((item) => {
            item.classList.remove("active-option");
        });
        navigationMenu[index].classList.add("active-option");
    }
})();


