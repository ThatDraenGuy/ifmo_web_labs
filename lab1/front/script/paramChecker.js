import { update } from "./graph.js";
window.paramChanged = paramChanged;
window.validateSubmission = validateSubmission;
const messageClass = "input-message";
const inputClassesMap = new Map();
inputClassesMap.set("text-input",validateTextInput);
inputClassesMap.set("radio-input",validateRadioInput);
const currentParams = new Map();

function paramChanged(name) {
    var message = getElementOfClassByName(messageClass,name);
    message.style.visibility = 'hidden'
    let res = checkParam(name);
    if (res) update(currentParams);
}

function checkParam(name) {
    for (const[className,validationFunction] of inputClassesMap) {
        var input = getElementOfClassByName(className,name);
        if (input!=null) return validationFunction(input);
    }
    return false;
}
function checkParams() {
    var result = true;
    for (const[className,validationFunction] of inputClassesMap) {
        if (!checkInputs(className,validationFunction)) result = false;
    }
    if (result) update(currentParams);
    return result;
}

function checkInputs(inputClass, validationFunction) {
    var inputs = getElementsOfClass(inputClass);
    var result = true;
    for (let input of inputs) {
        if (!validationFunction(input)) result=false;
    }
    return result;
}



function validateTextInput(textInput) {
    const value = textInput.value;
    const name = textInput.name;
    if (value=="") {
        message("No value inputted!",name);
        return false;
    }
    if (isNaN(value)) {
        message("Value should be a number!",name)
        return false;
    }
    if (value < Number(textInput.dataset.min) || value > Number(textInput.dataset.max)) {
        message("Value out of range!",name)
        return false;
    }
    message("All good!",name)
    currentParams.set(name, value);
    return true;
}
function validateRadioInput(radioInput) {
    const name = radioInput.name;
    var checkRadio = document.querySelector('input[name='+name+']:checked');
    if (checkRadio!=null) {
        message("All good!",name)
        currentParams.set(name, checkRadio.value);
        return true;
    }
    message("No value inputted!",name);
    currentParams.set(name, null);
    return false;
}
function message(message, name) {
    var field = getElementOfClassByName(messageClass,name)
    field.innerText = message;
    field.style.visibility = 'visible';
}

function validateSubmission() {
    return checkParams();
}

function getElementsOfClass(className) {
    return document.getElementsByClassName(className);
}
function getElementOfClassByName(className, elementName) {
    return getElementsOfClass(className).namedItem(elementName);
}