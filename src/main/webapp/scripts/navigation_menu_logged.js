const profilePic = document.getElementById("profile-pic");
const profileMenu = document.getElementById("profile-menu");
const profileMenuPic = document.getElementById("pm-pic");

function hideElement(element) {
    element.classList.toggle("hidden");
}

profilePic.addEventListener("click", () => {
    hideElement(profileMenu);
});

profileMenuPic.addEventListener("click", () => {
    hideElement(profileMenu);
});