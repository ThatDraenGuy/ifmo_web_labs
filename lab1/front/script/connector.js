import { reDraw, updateQuadrant } from "./graph.js";
import { resizeIframe } from "./utils.js";

const quadrantsKey = "quadrants";
const type = "type";
const x_sign = "x_sign";
const y_sign = "y_sign";

const constraintsKey = "constraints";
const constraints = new Map();
const options = "options";
const range = "range";
const rangeMin = "range_min";
const rangeMax = "range_max";
constraints.set(options, handleOptions);
constraints.set(range, handleRange);

var oReq = new XMLHttpRequest();
oReq.onload = function() {
    console.log(this.responseText);
    handleResponse(this.responseText);  
}
oReq.open("get", "back/connector.php", true);
oReq.send();


function handleResponse(responseText) {
    const obj = JSON.parse(responseText);
    console.log(obj);
    if (quadrantsKey in obj) {
        obj[quadrantsKey].forEach(element => {
            if (type in element && x_sign in element && y_sign in element) {
                updateQuadrant(element[type], element[x_sign], element[y_sign]);
            }
        });
        reDraw();
    }
    if (constraintsKey in obj) {
        console.log(obj[constraintsKey]);
        let constraintsReq = obj[constraintsKey];
        Object.keys(constraintsReq).forEach(key => {
            let element = constraintsReq[key];
            let func = constraints.get(element[type]);
            handleVariant(func, key, element);
        });
        insertVariant('<tr><td><input type="submit" value="shoot!"></td></tr>');
    }
}

function handleVariant(func, name, data) {
    let res = '<tr class="shoot-row"><td>' + name.toUpperCase()+":\n";
    res += func(name, data);
    res += '<br><span class="input-message" name="' + name + '" id="message" style="visibility: hidden;">message</span></td></tr>';
    insertVariant(res);
}


function handleOptions(name, data) {
    let optionsArray = data[options];
    let res = "";
    optionsArray.forEach(element => {
        res +=' ' + element + '<input type="radio" name="' + name + '" value="' + element + '" class="radio-input"  oninput="paramChanged(name)">\n'
    });
    return res;
}

function handleRange(name, data) {
    let min = data[rangeMin];
    let max = data[rangeMax];
    return '<input type="text" name="' + name + '" class="text-input" data-min="' + min + '"; data-max="' + max + '"; oninput="paramChanged(name)">\n';
}

function insertVariant(text) {
    const place = document.querySelector('#shoot-table');
    place.insertAdjacentHTML('beforeend', text);
}

export function sendShootingReq(x,y,r) {
    const formData = new FormData();
    formData.append('x',x);
    formData.append('y',y);
    formData.append('r',r);
    formData.append('shoot','true');

    let req = new XMLHttpRequest();
    req.onload = function() {
        let resFrame = document.getElementById('result');
        let res = resFrame.contentWindow.document;
        res.open();
        res.write(this.responseText);
        res.close();
        resizeIframe(resFrame);
    }
    req.open('POST','back/main.php');
    req.send(formData);
}