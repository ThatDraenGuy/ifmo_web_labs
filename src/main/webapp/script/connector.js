import { reDraw, setOldPoints, updateQuadrant } from "./graph.js";
import { ajax, resizeIframe } from "./utils.js";

// constants for getting info from server
const quadrantsKey = "quadrants";
const type = "type";
const xMul = "xMul";
const yMul = "yMul";

const constraintsKey = "constraints";
const constraints = new Map();
const options = "options";
const range = "range";
const rangeMin = "min";
const rangeMax = "max";
constraints.set(options, handleOptions);
constraints.set(range, handleRange);

const historyKey = "history";

// request data from server
const formData = new FormData();
formData.append("getData", "true");
ajax(formData, function(responseText) {
    handleResponse(responseText);  
})


function handleResponse(responseText) {
    console.log(responseText);
    try {
        var obj = JSON.parse(responseText);
    } catch (e) {
        alert('server is stoopid and sent non-valid JSON');
        return;
    }
    // get quadrants info and pass them to graph.js
    if (quadrantsKey in obj) {
        obj[quadrantsKey].forEach(element => {
            if (type in element && xMul in element && yMul in element) {
                updateQuadrant(element[type], element[xMul], element[yMul]);
            }
        });
        reDraw();
    }
    // get constraints info and insert them into document
    if (constraintsKey in obj) {
        let constraintsReq = obj[constraintsKey];
        Object.keys(constraintsReq).forEach(key => {
            let element = constraintsReq[key];
            let func = constraints.get(element[type]);
            handleVariant(func, key, element);
        });
        insertVariant('<tr><td colspan="2" class="button-coord-cell"><input class="shoot-button" type="submit" value="shoot!"></td></tr>');
        clearPlaceholder();
    }
    // get history info and update points on graph
    if (historyKey in obj) {
        setOldPoints(obj[historyKey]);
    }
}
// functions for inserting constraints in document
function handleVariant(func, name, data) {
    let res = '<tr class="shoot-row"><td class="name-coord-cell">' + name.toUpperCase()+':</td><td class="choice-coord-cell">';
    res += func(name, data);
    res += '</td><tr class="message-row"><td colspan="2"><span class="message-coord-cell input-message" name="' + name + '" id="message" style="visibility: hidden;">message</span></td></tr>';
    insertVariant(res);
}
function handleOptions(name, data) {
    let optionsArray = data[options];
    let res = "";
    optionsArray.forEach(element => {
        res += ` ${element} <input type="radio" name="${name}" value="${element}" class="radio-input"  oninput="paramChanged(name)">\n`
    });
    return res;
}
function handleRange(name, data) {
    let min = data[rangeMin];
    let max = data[rangeMax];
    return `<input type="text" name="${name}" class="text-input" placeholder="number from ${min} to ${max}" data-min="${min}"; data-max="${max}"; oninput="paramChanged(name)">\n`;
}

function insertVariant(text) {
    const place = document.querySelector('#shoot-table');
    place.insertAdjacentHTML('beforeend', text);
}
function clearPlaceholder() {
    const placeholder = document.querySelector('#shoot-load');
    placeholder.hidden = 'true';
}