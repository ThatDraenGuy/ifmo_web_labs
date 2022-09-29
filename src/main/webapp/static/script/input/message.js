export default class Message extends HTMLElement{
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
