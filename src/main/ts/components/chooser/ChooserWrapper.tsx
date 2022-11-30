import React, {FC} from "react";
import {RootState} from "../../store";
import {ActionCreatorWithOptionalPayload} from "@reduxjs/toolkit";

export interface ChooserWrapperProps {
    chooserName: string,
    children: React.ReactNode
}

export interface ChooserParams {
    getValue: (state: RootState) => number,
    setValue: ActionCreatorWithOptionalPayload<number, any>
}

export const ChooserWrapper: FC<ChooserWrapperProps> = ({chooserName, children}) => {
    return (
        <table>
            <tr>
                <td>{chooserName}</td>
                <td>{children}</td>
            </tr>
        </table>
    )
}