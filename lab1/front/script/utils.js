import { setOldPoints } from "./graph.js";


window.resizeIframe = resizeIframe;
window.clearHistory = clearHistory;

export function resizeIframe(iframe) {
    iframe.style.height = 0;
    iframe.style.height = iframe.contentWindow.document.body.scrollHeight+20 + 'px';
    iframe.contentWindow.clearHistory = clearHistory;
    iframe.parentElement.hidden = false;
}

function clearHistory() {
    const formData = new FormData();
    formData.append('clearHistory','true');
    let req = new XMLHttpRequest();
    req.onload = function() {
        let resFrame = document.getElementById('result').contentWindow.document;
        let history = resFrame.getElementById('history');
        history.remove();
        setOldPoints([]);
        // resFrame.open();
        // resFrame.write(this.responseText);
        // resFrame.close();
    }
    req.open('POST','back/main.php');
    req.send(formData);
}