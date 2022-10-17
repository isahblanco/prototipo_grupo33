const recipePage = document.getElementById("recipe-page");
const saveButton = document.getElementById("recipe-save-button");
const scoreModificationButtons = document.getElementById("recipe-score-modification").querySelectorAll("button");
const score = document.getElementById("recipe-score");
const upvoteButton = scoreModificationButtons[0];
const downvoteButton = scoreModificationButtons[1];

const isSaved = () => {
    const savedRequest = new XMLHttpRequest();
    savedRequest.open("GET", "is_saved?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client, true);
    savedRequest.onreadystatechange = () => {
        if (savedRequest.readyState === 4) {
                if (savedRequest.status === 200) {
                    if (savedRequest.responseText === "true") markRecipeAsSaved();
                }
            }
    };
    savedRequest.send(null);
};

const isRated = () => {
    const scoreRequest = new XMLHttpRequest();
    scoreRequest.open("GET", "is_scored?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client, true);
    scoreRequest.onreadystatechange = () => {
        if (scoreRequest.readyState === 4) {
                if (scoreRequest.status === 200) {
                    if (scoreRequest.responseText !== "not-rated") {
                        if (scoreRequest.responseText === "1") markRecipeUpvoted();
                        else if (scoreRequest.responseText === "-1") markRecipeDownvoted();
                    }
                }
            }
    };
    scoreRequest.send(null);
}

isSaved();
isRated();

saveButton.addEventListener("click", () => {
    if (!saveButton.classList.contains("saved")) {
        const savedRequest = new XMLHttpRequest();
        savedRequest.open("GET", "save_recipe?id=" + recipePage.dataset.recipe, true);
        savedRequest.onreadystatechange = () => {
            if (savedRequest.readyState === 4) {
                if (savedRequest.status === 200) {
                    markRecipeAsSaved();
                }
            }
        };
        savedRequest.send(null);
        
    } else {
        const savedRequest = new XMLHttpRequest();
        savedRequest.open("GET", "delete_recipe?saved_recipe=" + recipePage.dataset.recipe, true);
        savedRequest.onreadystatechange = () => {
            if (savedRequest.readyState === 4) {
                if (savedRequest.status === 200) {
                    markRecipeAsUnsaved();
                }
            }
        };
        savedRequest.send(null);
    }
});

upvoteButton.addEventListener("click", () => {
    if (!upvoteButton.classList.contains("voted")) {
        const scoreRequest = new XMLHttpRequest();
        scoreRequest.open("GET", "rate_recipe?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client + "&score=1", true);
        scoreRequest.onreadystatechange = () => {
            if (scoreRequest.readyState === 4) {
                    if (scoreRequest.status === 200) {
                        if (downvoteButton.classList.contains("voted")) score.textContent = parseInt(score.textContent) + 2;
                        else score.textContent = parseInt(score.textContent) + 1;
                        markRecipeUpvoted();
                    }
                }
        };
        scoreRequest.send(null);
    } else {
        const scoreRequest = new XMLHttpRequest();
        scoreRequest.open("GET", "rate_recipe?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client + "&score=0", true);
        scoreRequest.onreadystatechange = () => {
            if (scoreRequest.readyState === 4) {
                    if (scoreRequest.status === 200) {
                        if (scoreRequest.responseText !== "not-rated") {
                            score.textContent = parseInt(score.textContent) - 1;
                            markRecipeNotVoted();
                        }
                    }
                }
        };
        scoreRequest.send(null);
    }
    
});

downvoteButton.addEventListener("click", () => {
    if (!downvoteButton.classList.contains("voted")) {
        const scoreRequest = new XMLHttpRequest();
        scoreRequest.open("GET", "rate_recipe?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client + "&score=-1", true);
        scoreRequest.onreadystatechange = () => {
            if (scoreRequest.readyState === 4) {
                    if (scoreRequest.status === 200) {
                        if (upvoteButton.classList.contains("voted")) score.textContent = parseInt(score.textContent) - 2;
                        else score.textContent = parseInt(score.textContent) - 1;
                        markRecipeDownvoted();
                    }
                }
        };
        scoreRequest.send(null);
    } else {
        const scoreRequest = new XMLHttpRequest();
        scoreRequest.open("GET", "rate_recipe?recipe=" + recipePage.dataset.recipe + "&client=" + recipePage.dataset.client + "&score=0", true);
        scoreRequest.onreadystatechange = () => {
            if (scoreRequest.readyState === 4) {
                    if (scoreRequest.status === 200) {
                        if (scoreRequest.responseText !== "not-rated") {
                            score.textContent = parseInt(score.textContent) + 1;
                            markRecipeNotVoted();
                        }
                    }
                }
        };
        scoreRequest.send(null);
    }
});

function markRecipeAsSaved() {
    saveButton.classList.add("saved");
    saveButton.textContent = "Saved";
}

function markRecipeAsUnsaved() {
    saveButton.classList.remove("saved");
    saveButton.textContent = "Save";
}

function markRecipeUpvoted() {
    upvoteButton.classList.add("voted");
    downvoteButton.classList.remove("voted");
}

function markRecipeDownvoted() {
    upvoteButton.classList.remove("voted");
    downvoteButton.classList.add("voted");
}

function markRecipeNotVoted() {
    upvoteButton.classList.remove("voted");
    downvoteButton.classList.remove("voted");
}



