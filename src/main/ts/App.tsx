import React from "react";

type MyProps = {
    message: string
}

type AppState = {

}

export default class App extends React.Component<MyProps, any> {

    onSubmit = (e: React.FormEvent<HTMLFormElement>) : void => {
        e.preventDefault();
        const data = {
            "x": 1,
            "y": 1,
            "r": 1
        };
        fetch("/areacheck/shoot", {method: 'POST', headers: {"Content-Type": "application/json"}, body: JSON.stringify(data)})
    }
    render() {
        return (
            <div>
                {this.props.message} yooo
                <form action="/areacheck/shoot" onSubmit={this.onSubmit}>
                    <input type="submit"/>
                </form>
                huh?
                huh2
            </div>
        )
    }
}