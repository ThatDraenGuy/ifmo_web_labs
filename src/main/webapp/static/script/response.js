import {ajax} from "./utils.js";

document.getElementById("clear-button").addEventListener("click", clearHistory);
function clearHistory() {
    const formData = new FormData();
    formData.append('clearHistory','true');
    ajax(formData, function() {
        let history = document.getElementById('history');
        history.remove();
    })
}