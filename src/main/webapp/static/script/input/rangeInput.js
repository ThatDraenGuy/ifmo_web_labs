import CheckedInput from "./checkedInput.js";
export default class RangeInput extends CheckedInput {
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
        input.setAttribute("maxlength", "5");
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
