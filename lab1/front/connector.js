import { graphVariants, getQuadrantByName } from "./graph.js";
const quadrantsKey = "quadrants";
const constraintsKey = "constraints";
const type = "type";
const x_sign = "x_sign";
const y_sign = "y_sign";

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
        console.log(obj[quadrantsKey]);
        obj[quadrantsKey].forEach(element => {
            if (type in element && x_sign in element && y_sign in element) {
                graphVariants.set(""+element[x_sign]+element[y_sign], getQuadrantByName(element[type]));
            }
        });
        console.log(graphVariants)
    }
}