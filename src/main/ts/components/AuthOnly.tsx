import {FC, ReactNode} from "react";
import {useAppDispatch, useAppSelector} from "../hooks";
import {Navigate} from "react-router";
import {useGetCurrentQuery} from "../services/auth";
import {setAuth} from "../slices/authSlice";

export interface AuthOnlyProps {
    elseUrl: string,
    inverse: boolean
    children: ReactNode
}

export const AuthOnly: FC<AuthOnlyProps> = ({elseUrl, inverse, children}) => {
    const dispatch = useAppDispatch();
    const {currentData, isSuccess, isError, isLoading} = useGetCurrentQuery();
    const user = useAppSelector(state => state.auth.user);


    if (user) {
        if (!inverse) return (<>{children}</>)
        return (<Navigate to={elseUrl}/>)
    }

    if (isLoading) return (<></>)//TODO

    if (isSuccess) {
        dispatch(setAuth(currentData));
    }

    if ((!inverse && isError) || (inverse && !isError)) return (<Navigate to={elseUrl}/>)
    return (<div>{children}</div>)



    // if ((user && !inverse) || (isLoading && inverse)) return (<>{children}</>)
    //
    // if (isSuccess) {
    //     dispatch(setAuth(currentData));
    // }
    // // const token = useAppSelector(state => state.auth.token);
    //
    // if ((!inverse && isError) || (inverse && !isError)) return (<Navigate to={elseUrl}/>)
    // return (<div>{children}</div>)
}