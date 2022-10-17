const tagsInput = document.getElementById("nr-tags");
const currentTags = document.getElementById("new-recipe-current-tags");
const visibilityCheckbox = document.getElementById("nr-visibility");
const visibilityInput = document.getElementById("nr-hidden-visibility");
const confirmButton = document.getElementById("confirm-button");
const tagsSelectLabel = document.getElementById("new-recipe-tags-label");
const tagsSelect = document.getElementById("new-recipe-tags");
const fileInput = document.getElementById("nr-multimedia");
const image = document.getElementById("nr-multimedia-img");

disableSubmit();

function loadTags(){
    let tags = tagsInput.value.split(" ");
    tagsInput.value="";
    if (tags[0]!==""){
        tagsSelect.required = false;
        enableSubmit();
        tags.forEach((tag)=>{
            tag = tag.toUpperCase();
            let newLabel = document.createElement("label");
            let t = document.createTextNode(tag);
            newLabel.appendChild(t);
            newLabel.setAttribute("onclick", "delTag(this)");
            newLabel.setAttribute("value", tag);
            currentTags.appendChild(newLabel);
            tagsInput.value= tagsInput.value + ',' +tag;
        });
    }
}
loadTags();

function addTag(tag){
    tag = tag.toUpperCase();
    let tagsArray=(tagsInput.value).split(',');
    enableSubmit();

    if(!tagsArray.includes(tag) && tagsArray.length < 3){
        tagsArray.push(tag);
        hiddenValue=tagsArray.toString();
        tagsInput.setAttribute("value", hiddenValue);

        let newLabel = document.createElement("label");
        let t = document.createTextNode(tag);
        newLabel.appendChild(t);
        newLabel.setAttribute("onclick", "delTag(this)");
        newLabel.setAttribute("value", tag);
        currentTags.appendChild(newLabel);
    }
    tagsSelect.value = "";
}

function delTag(tag){
    let tagsArray=(tagsInput.value).split(',');
    let labelText = tag.text;
    let index = tagsArray.indexOf(labelText);
    tagsArray.splice(index, 1);
    if (tagsArray.length === 1) {
        disableSubmit();
    }
    let value=tagsArray.toString();
    tagsInput.setAttribute("value", value);
    tag.remove();
}

function enableSubmit() {
    confirmButton.classList.remove("disabled");
    tagsSelectLabel.classList.remove("empty");
    confirmButton.disabled = false;
    tagsSelectLabel.textContent = "Tags:";
}

function disableSubmit() {
    confirmButton.classList.add("disabled");
    confirmButton.disabled = true;
    tagsSelectLabel.classList.add("empty");
    tagsSelectLabel.textContent = "Tags (At least one):";
}

visibilityCheckbox.addEventListener("click", () => {
    let checked = visibilityCheckbox.checked;
    if (checked) visibilityInput.value = "active";
    else visibilityInput.value = "inactive";
});

fileInput.addEventListener("change", () => {
    const reader = new FileReader();
    reader.onload = (function(aImg) { 
        return function(e) { aImg.src = e.target.result; };
    })(image);
    reader.readAsDataURL(fileInput.files[0]);
});
