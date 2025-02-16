import React from "react";
import {HomePage} from "@/containers/HomePage";
import {ContactPage} from "@/containers/ContactPage/ContactPage";
import {CartPage} from "@/containers/CartPage";
import {RouteProps} from "react-router-dom";


export const PAGES = {
    ROOT: '/',
    CONTACT: '/contact',
    CART: '/cart',
}
export const ROUTES: (Omit<RouteProps, 'element'> & { Component: React.ComponentType })[] = [
    {
        path: PAGES.ROOT,
        Component: HomePage,
    },
    {
        path: PAGES.CONTACT,
        Component: ContactPage,
    },
    {
        path: PAGES.CART,
        Component: CartPage,
    }
];


