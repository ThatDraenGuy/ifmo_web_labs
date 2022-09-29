import RangeInput from "./rangeInput.js";

export default class QuadrantRangeChooser extends RangeInput {
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
