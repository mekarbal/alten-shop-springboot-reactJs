import React from 'react';
import { Typography } from '@mui/material';
import AdbIcon from '@mui/icons-material/Adb';

interface LogoProps {
    variant: 'h5' | 'h6';
    display: { xs: string; md: string };
}

const Logo: React.FC<LogoProps> = ({ variant, display }) => {
    return (
        <>
            <AdbIcon sx={{ display, mr: 1 }} />
            <Typography
                variant={variant}
                noWrap
                component="a"
                href="#app-bar-with-responsive-menu"
                sx={{
                    mr: 2,
                    display,
                    fontFamily: 'monospace',
                    fontWeight: 700,
                    letterSpacing: '.3rem',
                    color: 'black',
                    textDecoration: 'none',
                }}
            >
                Alten SHOP
            </Typography>
        </>
    );
};

export default Logo;