import CheckedInput from "./checkedInput.js";

export default class CheckboxOptionsInput extends CheckedInput {
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
