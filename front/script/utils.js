import superagent from 'superagent';
import { setOldPoints } from "./graph.js";

// import '../style/content.css';
// import '../style/header.css';
// import '../style/main.css';

// import '../../img/arrow.png';
// import '../../img/chihuahua-spin.gif';
// import '../../img/giphy.gif';
// import '../../img/github.png';
// import '../../img/load.gif';

window.resizeIframe = resizeIframe;
window.clearHistory = clearHistory;
window.showHeader = showHeader;
let counter = 0;
export function resizeIframe(iframe) {
    iframe.style.height = 0;
    iframe.style.height = iframe.contentWindow.document.body.scrollHeight+20 + 'px';
    iframe.contentWindow.clearHistory = clearHistory;
    if (counter==0) {
        counter++;
        return;
    }
    iframe.parentElement.hidden = false;
}

// yes, it would be better to use GET not POST in a bunch of situations, but the task said POST
export function ajax(formData, successFunc) {
    superagent
        .post('./back/main.php')
        .send(formData)
        .end((err,res) => {
            if  (err || !res.ok) {
                alert('oh no, ajax problem');
            } else {
                successFunc(res.text);
            }
        })
}

function clearHistory() {
    const formData = new FormData();
    formData.append('clearHistory','true');
    ajax(formData, function(responseText) {
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

export function sendShootingReq(x,y,r) {
    const formData = new FormData();
    formData.append('x',x);
    formData.append('y',y);
    formData.append('r',r);
    formData.append('shoot','true');
    ajax(formData, function(responseText) {
        let resFrame = document.getElementById('result');
        let res = resFrame.contentWindow.document;
        res.open();
        res.write(responseText);
        res.close();
        resizeIframe(resFrame);
    })
}