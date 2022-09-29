import Message from "./input/message.js";
import CheckedInput from "./input/checkedInput.js";
import RangeInput from "./input/rangeInput.js";
import {updateGraph} from "./graph.js";
import CheckboxOptionsInput from "./input/checkboxOptions.js";
import QuadrantChooser from "./input/quadrantChooser.js";
import QuadrantRangeChooser from "./input/quadrantRange.js";

customElements.define("message-input", Message);
customElements.define("checked-input", CheckedInput);
customElements.define("range-input", RangeInput);
customElements.define("checkbox-options-input", CheckboxOptionsInput);
customElements.define("quadrant-range-chooser", QuadrantRangeChooser);
customElements.define("quadrant-chooser", QuadrantChooser);

const type = "type";
const constraintsConstructors = new Map();
const options = "options";
const radioOptions = "radio_options";
const checkboxOptions = "checkbox_options";
const range = "range";
const rangeMin = "min";
const rangeMax = "max";
export class FormHandler {
    form;
    place;
    constraints = new Map();
    constructor(info, form, place) {
        this.form = form;
        this.place = place;
        constraintsConstructors.set(radioOptions, this.handleRadioOptions);
        constraintsConstructors.set(range, this.handleRange);
        constraintsConstructors.set(checkboxOptions, this.handleCheckboxOptions)

        this.clearVariantSpace();
        Object.keys(info).forEach(key => {
            let element = info[key];
            let func = constraintsConstructors.get(element[type]);
            this.handleVariant(func, key, element);
        });
        this.setSubmit();
        this.clearPlaceholder();
        this.setupForm();
    }
    // functions for inserting constraints in document
    handleVariant(construct, name, data) {
        const element = construct(name, data);
        element.setAttribute("display-name", `${name.toUpperCase()}:`);
        element.setAttribute("choice-row-class", "shoot-row");
        element.setAttribute("name-cell-class", "name-coord-cell");
        element.setAttribute("choice-cell-class", "choice-coord-cell");
        element.setAttribute("message-row-class", "message-row");
        element.setAttribute("message-class", "message-coord-cell input-message");
        element.setAttribute("choice-row-class", "shoot-row");


        this.constraints.set(name, element);
        this.place.appendChild(element);
    }
    handleRadioOptions(name, data) {
        let optionsArray = data[options];
        let res = "";
        optionsArray.forEach(element => {
            res += ` ${element} <input type="radio" name="${name}" value="${element}" class="radio-input"  oninput="paramChanged(name)">\n`
        });
        return res;
    }
    handleCheckboxOptions(name, data) {
        let optionsArray = data[options];
        const input = new CheckboxOptionsInput(name, optionsArray);
        input.setAttribute("choice-class", "checkbox-input");
        return input;
    }
    handleRange(name, data) {
        let min = data[rangeMin];
        let max = data[rangeMax];
        const input = new RangeInput(name, min, max);
        input.setAttribute("choice-class", "text-input");
        return input;
    }

    insertVariant(text) {
        // const place = document.querySelector('#shoot-table');
        this.place.insertAdjacentHTML('beforeend', text);
    }
    setSubmit() {
        this.insertVariant('<tr><td colspan="2" class="button-coord-cell"><input class="shoot-button" type="submit" value="shoot!"></td></tr>');
    }
    clearVariantSpace() {
        // const place = document.querySelector('#shoot-table');
        this.place.innerHTML = '';
    }
    clearPlaceholder() {
        const placeholder = document.querySelector('#shoot-load');
        placeholder.hidden = 'true';
    }
    setupForm() {
        this.form.addEventListener('submit', (e) => {
            this.validateSubmission(e);
        })
    }
    validateSubmission(event) {
        event.preventDefault();
        let result = true;
        const currentParams = new Map();
        this.constraints.forEach((element, name) => {
            currentParams.set(name, element.currentParam);
            if (! element.validate()) result = false;
        });
        if (result) updateGraph(currentParams, true);
    }
}


