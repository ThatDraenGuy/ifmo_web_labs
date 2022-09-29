import QuadrantRangeChooser from "./quadrantRange.js";
export default class QuadrantChooser extends HTMLElement {
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
