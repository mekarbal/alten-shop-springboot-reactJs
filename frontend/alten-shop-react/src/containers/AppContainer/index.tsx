import {Route, Routes} from 'react-router-dom';
import {ROUTES} from "@/core/routes";
import Header from "@/containers/AppContainer/components/Header";
import {Suspense} from "react";

type Props = {};
export const AppContainer = (props: Props) => {
    return (<>
            <Header/>
            <Suspense fallback={"Loading ....."}>
                <Routes>
                    {ROUTES.map(({path, Component}, idx) => (<Route key={idx} path={path} element={<Component/>}/>))}
                </Routes>
            </Suspense>
        </>

    );
};