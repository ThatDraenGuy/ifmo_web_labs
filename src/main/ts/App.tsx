import React from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Login} from "./components/Login";
import {Home} from "./components/Home"
import Header from "./components/Header";

export default class App extends React.Component<any, any> {
    render() {

        return (
            <BrowserRouter>
                <Header/>
                <Routes>
                    <Route path="/login" element={<Login/>}/>
                    <Route path="/" element={<Home/>}/>
                </Routes>
            </BrowserRouter>
        )
    }
}