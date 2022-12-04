import {FC} from "react";
import {Button} from "react-bootstrap";
import {useLogoutMutation} from "../../services/auth";
import {useAppSelector} from "../../hooks";


export const LogoutButton: FC<any> = () => {
    const [logoutPost, {}] = useLogoutMutation();
    const userId = useAppSelector(state => state.auth.user.id)
    return (
        <Button onClick={() => logoutPost(userId)}>Logout</Button>
    )
}