import React from 'react';
import { MenuItem, Typography, Button } from '@mui/material';
import { useNavigate } from 'react-router-dom';

interface MenuItemsProps {
    items: { path: string; label: string }[];
    onClose: () => void;
    isMobile?: boolean;
}

const MenuItems: React.FC<MenuItemsProps> = ({ items, onClose, isMobile = false }) => {
    const navigate = useNavigate();

    const handleClick = (path: string) => {
        navigate(path);
        onClose();
    };

    return (
        <>
            {items.map((item) =>
                isMobile ? (
                    <MenuItem key={item.path} onClick={() => handleClick(item.path)}>
                        <Typography sx={{ textAlign: 'center' }}>{item.label}</Typography>
                    </MenuItem>
                ) : (
                    <Button
                        key={item.path}
                        onClick={() => handleClick(item.path)}
                        sx={{ my: 2, color: '#000000', display: 'block' }}
                    >
                        {item.label}
                    </Button>
                ),
            )}
        </>
    );
};

export default MenuItems;