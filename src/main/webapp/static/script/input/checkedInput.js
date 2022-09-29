import Message from "./message.js";
import {updateValue} from "../graph.js";

export default class CheckedInput extends HTMLElement {
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