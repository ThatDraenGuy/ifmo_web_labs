import {FC, useState} from "react";
import {Button, Offcanvas} from "react-bootstrap";
import {UserDisplay} from "./UserDisplay";

export interface SideMenuProps {
    isShown: boolean,
    setIsShown: (set: boolean) => void
}

export const SideMenu: FC<SideMenuProps> = ({isShown, setIsShown}) => {

    return(
        <>


            <Offcanvas show={isShown} onHide={() => setIsShown(false)}>
                <Offcanvas.Header closeButton>
                    Menu
                </Offcanvas.Header>
                <Offcanvas.Body>
                    <UserDisplay onLogoutClick={() => setIsShown(false)}/>
                </Offcanvas.Body>
            </Offcanvas>
        </>
    )
}