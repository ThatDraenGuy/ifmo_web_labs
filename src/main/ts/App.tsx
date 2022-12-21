import React, {FC} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Login} from "./components/starter/Login";
import {Home} from "./components/Home"
import {Header} from "./components/Header";
import {AuthOnly} from "./components/AuthOnly";
import {Starter} from "./components/starter/Starter";
import {Error} from "./components/Error";


export const App: FC<any> = () => {
    return (
        <BrowserRouter>
            <Header/>
            <Routes>
                <Route path="/login" element={
                    <AuthOnly elseUrl={"/"} inverse={true}>
                        <Starter/>
                    </AuthOnly>
                }/>
                <Route path="/" element={
                    <AuthOnly elseUrl={"/login"} inverse={false}>
                        <Home/>
                    </AuthOnly>
                }/>
                <Route path="*" element={
                    <Error/>
                }/>
            </Routes>
        </BrowserRouter>
    )
}