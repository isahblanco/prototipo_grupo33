const tags = document.querySelectorAll(".tag-item");
const searchBar = document.getElementById("search-input");
const results = document.getElementById("search-results");


tags.forEach((item) => {
    item.addEventListener("click", () => {
        search(item.value);
    });
});

searchBar.addEventListener("focus", () => {
    searchBar.placeholder = "";
});

searchBar.addEventListener("blur", () => {
    searchBar.placeholder = "What are you looking for?";
});

searchBar.addEventListener("change", () => {
    search(searchBar.value);
    searchBar.value = "";
});

function search(input) {
    results.innerHTML = "";
    const searchRequest = new XMLHttpRequest();
    searchRequest.open("GET", "search?input=" + input, true);
    searchRequest.onreadystatechange = () => {
        if (searchRequest.readyState === 4) {
                if (searchRequest.status === 200) {
                    const recipes = JSON.parse(searchRequest.responseText);
                    recipes.forEach((recipe) => {
                        let recipeCard = document.createElement("div");
                        recipeCard.classList.add("search-result-item");
                        recipeCard.innerHTML = `<img src="images?id=${recipe.id}">\n`;
                        recipeCard.innerHTML += `<a href="recipeInfo?id=${recipe.id}">${recipe.name}</a>\n`;
                        results.append(recipeCard);
                    });
                }
            }
    };
    searchRequest.send(null);
}

