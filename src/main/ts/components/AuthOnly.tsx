import {FC, ReactNode} from "react";
import {useAppSelector} from "../hooks";
import {Navigate} from "react-router";

export interface AuthOnlyProps {
    elseUrl: string,
    inverse: boolean
    children: ReactNode
}

export const AuthOnly: FC<AuthOnlyProps> = ({elseUrl, inverse, children}) => {
    const token = useAppSelector(state => state.auth.token);

    if ((!inverse && !token) || (inverse && token)) return (<Navigate to={elseUrl}/>)
    return (<div>{children}</div>)
}