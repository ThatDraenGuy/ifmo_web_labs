import React from "react";
import App from "./App";
import {createRoot} from "react-dom/client";
import {Provider} from "react-redux";
import {store} from "./store";

createRoot(document.getElementById("react")).render(
    <Provider store={store}>
        <App message="it works"/>
    </Provider>)
