import ReactDOM from 'react-dom';
import React from "react";
import App from "./App";
import {createRoot} from "react-dom/client";

createRoot(document.getElementById("react")).render(<App message="it works"/>)
