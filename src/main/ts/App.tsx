import React from "react";

type MyProps = {
    message: string
}

type AppState = {

}

export default class App extends React.Component<MyProps, any> {
    render() {
        return (
            <div>
                {this.props.message} yooo
            </div>
        )
    }
}