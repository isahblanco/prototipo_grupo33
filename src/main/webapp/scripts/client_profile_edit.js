const profilePicInput = document.getElementById("user-new-pic");
const clientProfilePic = document.getElementById("user-pic");


profilePicInput.addEventListener("change", () => {
    const reader = new FileReader();
    reader.onload = (function(aImg) { 
        return function(e) { aImg.src = e.target.result; };
    })(clientProfilePic);
    reader.readAsDataURL(profilePicInput.files[0]);
});


