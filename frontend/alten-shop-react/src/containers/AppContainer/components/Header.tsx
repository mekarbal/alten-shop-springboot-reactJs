import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import Container from '@mui/material/Container';
import MenuIcon from '@mui/icons-material/Menu';
import Logo from '@/containers/AppContainer/components/Logo';
import MenuItems from './MenuItems';
import {PAGES} from "@/core/routes";
import {Badge, BadgeProps, styled} from "@mui/material";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import {useNavigate} from "react-router-dom";
import {useSelector} from "react-redux";
import {RootState} from "@/core/store/store";

const StyledBadge = styled(Badge)<BadgeProps>(({theme}) => ({
    '& .MuiBadge-badge': {
        right: -3, top: 13, border: `2px solid ${theme.palette.background.paper}`, padding: '0 4px',
    },
}));

const Header = () => {
    const navigate = useNavigate();
    const totalItems = useSelector((state: RootState) => state.cart.totalItems);

    const [anchorElNav, setAnchorElNav] = React.useState<null | HTMLElement>(null);

    const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorElNav(event.currentTarget);
    };

    const handleCloseNavMenu = () => {
        setAnchorElNav(null);
    };

    const navaigateToCart = () => {
        navigate('/cart');
    };

    const menuItems = [{path: PAGES.ROOT, label: 'Home'}, {path: PAGES.CONTACT, label: 'Contact'},];

    return (<AppBar position="static" sx={{background: 'transparent', boxShadow: 'none'}}>
        <Container maxWidth="xxl">
            <Toolbar disableGutters>
                <Logo variant="h6" display={{xs: 'none', md: 'flex'}}/>

                <Box sx={{flexGrow: 1, display: {xs: 'flex', md: 'none'}}}>
                    <IconButton
                        size="large"
                        aria-label="account of current user"
                        aria-controls="menu-appbar"
                        aria-haspopup="true"
                        onClick={handleOpenNavMenu}
                        sx={{color: '#000000'}}
                    >
                        <MenuIcon/>
                    </IconButton>
                    <Menu
                        id="menu-appbar"
                        anchorEl={anchorElNav}
                        anchorOrigin={{
                            vertical: 'bottom', horizontal: 'left',
                        }}
                        keepMounted
                        transformOrigin={{
                            vertical: 'top', horizontal: 'left',
                        }}
                        open={Boolean(anchorElNav)}
                        onClose={handleCloseNavMenu}
                        sx={{display: {xs: 'block', md: 'none'}}}
                    >
                        <MenuItems items={menuItems} onClose={handleCloseNavMenu} isMobile/>
                    </Menu>
                </Box>

                <Logo variant="h5" display={{xs: 'flex', md: 'none'}}/>

                <Box sx={{flexGrow: 1, display: {xs: 'none', md: 'flex'}}}>
                    <MenuItems items={menuItems} onClose={handleCloseNavMenu}/>
                </Box>


                <Box sx={{flexGrow: 0}}>
                    <IconButton aria-label="cart" onClick={navaigateToCart}>
                        <StyledBadge badgeContent={totalItems}
                                     color="secondary">
                            <ShoppingCartIcon/>
                        </StyledBadge>
                    </IconButton>
                </Box>
            </Toolbar>
        </Container>
    </AppBar>);
};

export default Header;