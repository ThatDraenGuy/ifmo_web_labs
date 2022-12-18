import {FC, ReactNode, useEffect} from "react";
import {Navigate} from "react-router";
import {useGetCurrentUserQuery} from "../services/auth";

export interface AuthOnlyProps {
    elseUrl: string,
    inverse: boolean
    children: ReactNode
}

export const AuthOnly: FC<AuthOnlyProps> = ({elseUrl, inverse, children}) => {
    const {isError, isLoading} = useGetCurrentUserQuery();

    if (isLoading) return (<></>)//TODO


    if ((!inverse && isError) || (inverse && !isError)) return (<Navigate to={elseUrl}/>)
    return (<div>{children}</div>)
}