import { setOldPoints } from "./graph.js";


window.resizeIframe = resizeIframe;
window.clearHistory = clearHistory;
window.showHeader = showHeader;

export function resizeIframe(iframe) {
    iframe.style.height = 0;
    iframe.style.height = iframe.contentWindow.document.body.scrollHeight+20 + 'px';
    iframe.contentWindow.clearHistory = clearHistory;
    iframe.parentElement.hidden = false;
}

//libs are for the weak (not really, just decided to make this the most vanilla js I could)
export function ajax(formData, onLoad) {
    let req = new XMLHttpRequest();
    req.onload = onLoad;
    req.open('POST', 'back/main.php');
    req.send(formData);
}

function clearHistory() {
    const formData = new FormData();
    formData.append('clearHistory','true');
    ajax(formData, function() {
        let resFrame = document.getElementById('result').contentWindow.document;
        let history = resFrame.getElementById('history');
        history.remove();
        setOldPoints([]);
    })
}

// don't have time to implement a normal solution
function showHeader(checkbox) {
    let header = document.getElementById('header');
    if (checkbox.checked) {
        header.className = 'blob header header-active';
    } else {
        header.className = 'blob header';
    }
}