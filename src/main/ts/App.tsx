import React, {FC} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Login} from "./components/Login";
import {Home} from "./components/Home"
import {Header} from "./components/Header";
import {AuthOnly} from "./components/AuthOnly";

export const App: FC<any> = () => {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/login" element={
                    <AuthOnly elseUrl={"/"} inverse={true}>
                        <Login/>
                    </AuthOnly>
                }/>
                <Route path="/" element={
                    <AuthOnly elseUrl={"/login"} inverse={false}>
                        <Home/>
                    </AuthOnly>
                }/>
            </Routes>
        </BrowserRouter>
    )
}