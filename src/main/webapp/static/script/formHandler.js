import {updateGraph, updateValue} from "./graph.js";

class Message extends HTMLElement{
    span
    constructor() {
        super();

    }

    connectedCallback() {
        this.span = document.createElement("span")

        // this.span.setAttribute("class", "message-coord-cell input-message");
        this.span.setAttribute("class", this.getAttribute("message-class"));

        this.span.setAttribute("style", "visibility: hidden;")
        this.span.style.transition = "opacity 2s 1s"

        this.appendChild(this.span);
    }

    message(message) {
        // window.cancelAnimationFrame(this.frame);
        this.span.innerText = message;
        this.span.style.transition = 'none';
        this.span.style.visibility = 'visible';
        this.span.style.opacity = "1";
        this.anim();
        // this.frame = this.animation(this.span);
    }
    clear() {
        this.span.style.visibility = 'hidden';
    }
    anim() {
        let span = this.span;
        setTimeout(() => {
            span.style.transition = "opacity 2s 1s"
            span.style.opacity = "0";
        }, 10)
    }
    animation(span) {
        let start = performance.now();

        return requestAnimationFrame(function animate(time) {
            let timeFraction = ((time - start) / 3000).toFixed(2);

            if (timeFraction > 0.5) {
                span.style.opacity = (1 - timeFraction)*2 + "";
            }

            if (timeFraction < 1) {
                requestAnimationFrame(animate);
            } else {
                span.style.visibility = 'hidden';
            }
        });
    }
}
customElements.define("message-input", Message);


class CheckedInput extends HTMLElement {
    messageElement;
    currentParam;
    name;
    constructor(name) {
        super();
        this.name = name;
        this.setAttribute("name", name);
    }

    connectedCallback() {
        if (this.name===undefined) this.name = this.getAttribute("name");

        const table = document.createElement("table");

        const row1 = document.createElement("tr");
        // row1.setAttribute("class", "shoot-row");
        row1.setAttribute("class", this.getAttribute("choice-row-class"));

        const nameCell = document.createElement("td");
        // nameCell.setAttribute("class","name-coord-cell");
        nameCell.setAttribute("class",this.getAttribute("name-cell-class"));
        nameCell.innerText = this.getAttribute("display-name");


        const choiceCell = document.createElement("td");
        // choiceCell.setAttribute("class","choice-coord-cell");
        choiceCell.setAttribute("class",this.getAttribute("choice-cell-class"));

        const row2 = document.createElement("tr");
        // row2.setAttribute("class", "message-row");
        row2.setAttribute("class", this.getAttribute("message-row-class"));

        this.messageElement = new Message();
        this.messageElement.setAttribute("message-class", this.getAttribute("message-class"));
        const messageCell = document.createElement("td");
        messageCell.setAttribute("colspan","2");

        // const message = document.createElement("message-input");
        // message.setAttribute("name", this.getAttribute("name"));

        this.appendChild(table);
        table.appendChild(row1)
        row1.appendChild(nameCell);
        row1.appendChild(choiceCell);

        const choice = this.getChoice();
        choiceCell.append(choice);

        table.appendChild(row2);
        row2.appendChild(messageCell);
        messageCell.appendChild(this.messageElement);

        // this.outerHTML = this.innerHTML;
    }

    attributeChangedCallback(name, oldValue, newValue) {
        this.onChanged();
    }
    static get observedAttributes() { return ['checked']; }

    getChoice() {
        return document.createElement("div");
    }

    message(message) {
        this.messageElement.message(message);
    }

    onChanged() {
        this.messageElement.clear();
        let res = this.validate();
        this.setAttribute("status", res+"");
        if (!res) this.currentParam = undefined;
        if (res) updateValue(this.name, this.currentParam);
    }
    validate() {
        return true;
    }
}
customElements.define("checked-input", CheckedInput);

class RangeInput extends CheckedInput {
    min
    max
    element
    constructor(name, min, max) {
        super(name);
        this.min = min;
        this.setAttribute("min", min);
        this.max = max;
        this.setAttribute("max", max);

    }

    connectedCallback() {
        if (this.min===null) this.min = this.getAttribute("min");
        if (this.max===null) this.max = this.getAttribute("max");
        super.connectedCallback();
    }

    getChoice() {
        const input = document.createElement("input");
        input.setAttribute("type", "text");
        input.setAttribute("name", this.name);

        // input.setAttribute("class", "text-input");
        input.setAttribute("class", this.getAttribute("choice-class"));

        input.setAttribute("placeholder", `number from ${this.min} to ${this.max}`);
        input.setAttribute("data-min", `${this.min}`);
        input.setAttribute("data-max", `${this.max}`);
        input.addEventListener('input', (e) => {
            this.onChanged(e);
        });
        this.element = input;
        return input;
    }

    validate() {
        const value = this.element.value;
        const name = this.element.name;
        if (value==="") {
            this.message("No value inputted!",name);
            return false;
        }
        if (isNaN(value)) {
            this.message("Value should be a number!",name)
            return false;
        }
        if (value < Number(this.min) || value > Number(this.max)) {
            this.message("Value out of range!",name)
            return false;
        }
        this.message("All good!",name);
        this.currentParam = value;
        return true;
    }
}
customElements.define("range-input", RangeInput);

class CheckboxOptionsInput extends CheckedInput {
    options;
    checkboxes = [];
    constructor(name, options) {
        super(name);
        this.options = options;
        this.setAttribute("options", options);
    }

    connectedCallback() {
        if (this.options===null) {
            const options = this.getAttribute("options");
            this.options = options.split(",");
        }
        super.connectedCallback();
    }

    getChoice() {
        const div = document.createElement("div");
        this.options.forEach((option) => {
            const label = document.createElement("span");
            label.innerText = option;
            const input = document.createElement("input");
            input.setAttribute("type", "checkbox");

            // input.setAttribute("class", "checkbox-input");
            input.setAttribute("class", this.getAttribute("choice-class"));


            input.setAttribute("name", this.name);
            input.setAttribute("value", option);
            input.addEventListener("change", (e) => {
                this.onChanged(e);
            });
            div.appendChild(label);
            div.appendChild(input);
            this.checkboxes.push(input);
        });
        return div;
    }

    validate() {
        let checked = [];
        for (const index in this.checkboxes) {
            const option = this.checkboxes[index];
            if (option.checked) checked.push(option);
        }
        if (checked.length > 1) {
            this.message("More than 1 value inputted!");
            return false;
        }
        if (checked.length === 0) {
            this.message("No value inputted!");
            return false;
        }
        this.message("All good!");
        this.currentParam = checked.pop().value;
        return true;
    }
}
customElements.define("checkbox-options-input", CheckboxOptionsInput);

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

class QuadrantRangeChooser extends RangeInput {
    constructor(name, min, max) {
        super(name, min, max);
    }
    getChoice() {
        const choice = super.getChoice();
        choice.setAttribute("placeholder", `${this.min} to ${this.max}`);
        return choice;
    }

    connectedCallback() {
        super.connectedCallback();
        this.message("\n")
    }

    validate() {
        const value = this.element.value;
        const name = this.element.name;
        if (value==="") {
            this.message("No value!",name);
            return false;
        }
        if (isNaN(value)) {
            this.message("Not a number!",name)
            return false;
        }
        if (value < Number(this.min) || value > Number(this.max)) {
            this.message("Out of range!",name)
            return false;
        }
        this.message("All good!",name);
        this.currentParam = value;
        return true;
    }
}
customElements.define("quadrant-range-chooser", QuadrantRangeChooser);

class QuadrantChooser extends HTMLElement {
    index;
    quadrants;
    coordChoosers = [];
    constructor() {
        super();
        this.index = this.getAttribute("index");
        this.quadrants = this.getAttribute("quadrants").split(",");
    }

    connectedCallback() {
        const table = document.createElement("table");

        const row1 = document.createElement("tr");
        const cell1 = document.createElement("td");
        const typeTable = document.createElement("table");
        const typeRow = document.createElement("tr");
        const typeLabelCell = document.createElement("td");
        const label = document.createElement("label");
        label.setAttribute("for", `type${this.index}`);
        label.innerText = "Type:";

        const typeChooserCell = document.createElement("td");
        const select = document.createElement("select");
        select.setAttribute("name", `quadrants[${this.index}][type]`);
        select.setAttribute("id", `type${this.index}`);

        for (const index in this.quadrants) {
            const quadrant = this.quadrants[index];
            const option = document.createElement("option");
            option.setAttribute("value", quadrant);
            option.innerText = quadrant;
            select.appendChild(option);
        }

        typeChooserCell.appendChild(select);
        typeLabelCell.appendChild(label);
        typeRow.appendChild(typeLabelCell);
        typeRow.appendChild(typeChooserCell);
        typeTable.appendChild(typeRow);
        cell1.appendChild(typeTable);
        row1.appendChild(cell1);
        table.appendChild(row1);

        const row2 = document.createElement("tr");
        const xCell = document.createElement("td");
        const xCoord = this.getCoordChooser("x");
        this.coordChoosers.push(xCoord);
        xCell.appendChild(xCoord);
        row2.appendChild(xCell);

        table.appendChild(row2);

        const row3 = document.createElement("tr");
        const yCell = document.createElement("td");
        const yCoord = this.getCoordChooser("y");
        this.coordChoosers.push(yCoord);
        yCell.appendChild(yCoord);
        row3.appendChild(yCell);

        table.appendChild(row3);

        this.appendChild(table);
    }

    attributeChangedCallback(name, oldValue, newValue) {
        this.coordChoosers.forEach((chooser) => {
            chooser.onChanged();
            this.setAttribute("status", chooser.getAttribute("status"));
        })
    }
    static get observedAttributes() { return ['checked']; }

    getCoordChooser(name) {
        let min = this.getAttribute(name+"-min");
        let max = Number.parseInt(min) + 1;
        const input = new QuadrantRangeChooser(`quadrants[${this.index}][${name}_mul]`, min, max);
        input.setAttribute("choice-class", "updater-input");
        input.setAttribute("display-name", name.toUpperCase()+":");
        return input;
    }
}
customElements.define("quadrant-chooser", QuadrantChooser);

