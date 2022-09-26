import {ajax, reload} from "./utils.js";

window.submit = submit;
const form = document.getElementById("updater-form");
form.addEventListener("submit", submit)

function submit(e) {
    e.preventDefault();
    let formData = new FormData(document.getElementById("updater-form"));
    ajax(formData, success, error);
    return false;
}

function success(resText) {
    reload();
}
function error(err) {
    alert("Insufficient quadrant data!");
}