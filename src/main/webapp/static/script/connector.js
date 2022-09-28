import { reDraw, setOldPoints, updateQuadrant } from "./graph.js";
import { ajax } from "./utils.js";
import {FormHandler} from "./formHandler.js";

// constants for getting info from server
const quadrantsKey = "quadrants";
const type = "type";
const xMul = "xMul";
const yMul = "yMul";

const constraintsKey = "constraints";

const historyKey = "history";

// request data from server
getData();
export function getData() {
    const formData = new FormData();
    formData.append("getData", "true");
    ajax(formData, handleResponse);
}


function handleResponse(responseText) {
    console.log(responseText);
    let obj;
    try {
        obj = JSON.parse(responseText);
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
        const place = document.querySelector('#shoot-table');
        const form = document.querySelector('#shooting-form');
        new FormHandler(constraintsReq, form, place);
    }
    // get history info and update points on graph
    if (historyKey in obj) {
        setOldPoints(obj[historyKey]);
    }
}