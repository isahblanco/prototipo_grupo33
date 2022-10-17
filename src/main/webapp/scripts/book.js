const myRecipesPage = document.getElementById("my-recipes-page");
const savedRecipesPage = document.getElementById("saved-recipes-page");
const myRecipesButton = document.getElementById("my-recipes-button");
const savedRecipesButton = document.getElementById("saved-recipes-button");
const deleteRecipesButton = document.querySelector(".recipe-card-delete-button");
const ownRecipeCardDeletionButtons = document.querySelectorAll("#book-own-recipe-card-deletion");
const savedRecipeCardDeletionButtons = document.querySelectorAll("#book-saved-recipe-card-deletion");

ownRecipeCardDeletionButtons.forEach((button) => {
    button.addEventListener("click", () => {
        if(confirm("Are you sure you want to delete this recipe?")){
            const savedRequest = new XMLHttpRequest();
            savedRequest.open("GET", "delete_recipe?own_recipe=" + button.dataset.id, true);
            savedRequest.onreadystatechange = () => {
                if (savedRequest.readyState === 4) {
                        if (savedRequest.status === 200) {
                            destroyRecipeCard(button.parentNode);
                        }
                    }
            };
            savedRequest.send(null);
        }
    });
});

savedRecipeCardDeletionButtons.forEach((button) => {
    button.addEventListener("click", () => {
        if(confirm("Are you sure you want to delete this recipe?")){
            const savedRequest = new XMLHttpRequest();
            savedRequest.open("GET", "delete_recipe?saved_recipe=" + button.dataset.id, true);
            savedRequest.onreadystatechange = () => {
                if (savedRequest.readyState === 4) {
                        if (savedRequest.status === 200) {
                            destroyRecipeCard(button.parentNode);
                        }
                    }
            };
            savedRequest.send(null);
        }
    });
})

savedRecipesButton.classList.toggle("selected");

myRecipesButton.addEventListener("click",()=>{
    myRecipesButton.classList.toggle("selected");
    savedRecipesButton.classList.toggle("selected");
    savedRecipesPage.classList.remove("selected");
    myRecipesPage.classList.add("selected");
    savedRecipesPage.classList.add("not-selected");
    myRecipesPage.classList.remove("not-selected");
});
    
savedRecipesButton.addEventListener("click",()=>{
    myRecipesButton.classList.toggle("selected");
    savedRecipesButton.classList.toggle("selected");
    savedRecipesPage.classList.add("selected");
    myRecipesPage.classList.remove("selected");
    savedRecipesPage.classList.remove("not-selected");
    myRecipesPage.classList.add("not-selected");
});

function destroyRecipeCard(element) {
    element.remove();
}


