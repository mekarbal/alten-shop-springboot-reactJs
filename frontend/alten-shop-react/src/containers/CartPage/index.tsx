// src/pages/CartPage.tsx

import React, {useMemo} from 'react';
import { Box, Button, Card, CardContent, Typography, Grid } from '@mui/material';
import { useDispatch, useSelector } from 'react-redux';
import AddIcon from '@mui/icons-material/Add';
import RemoveIcon from '@mui/icons-material/Remove';
import {RootState} from "@/core/store/store";
import {addToCart, decreaseOrRemove} from "@/core/features/cart/cartSlice";

export const CartPage = () => {
    const dispatch = useDispatch();

    const cartItems = useSelector((state: RootState) => state.cart.cartItems);
    const totalPrice = useMemo(
        () => cartItems.reduce((total, item) => total + item.price * item.qty, 0),
        [cartItems]
    );

    const handleIncrease = (product: CartItem) => {
        dispatch(addToCart(product));
    };

    const handleDecrease = (productId: string) => {
        dispatch(decreaseOrRemove(productId));
    };

    return (
        <Box sx={{ maxWidth: 800, margin: '0 auto', paddingTop: 2 }}>
            <Typography variant="h4" gutterBottom>
                Your Cart
            </Typography>

            {cartItems.length === 0 ? (
                <Typography variant="h6">Your cart is empty.</Typography>
            ) : (
                <Grid container spacing={2}>
                    {cartItems.map((item) => (
                        <Grid item xs={12} key={item.id}>
                            <Card sx={{ display: 'flex', alignItems: 'center' }}>
                                <CardContent sx={{ display: 'flex', justifyContent: 'space-between', width: '100%' }}>
                                    <Box sx={{ display: 'flex', alignItems: 'center', marginRight: 2 }}>
                                        <img
                                            src={item.image}
                                            alt={item.title}
                                            style={{
                                                width: 50,
                                                height: 50,
                                                objectFit: 'contain',
                                            }}
                                        />
                                    </Box>

                                    <Box sx={{ flex: 1 }}>
                                        <Typography variant="h6">{item.title}</Typography>
                                        <Typography variant="body2" color="text.secondary">
                                            Price: ${item.price}
                                        </Typography>
                                    </Box>

                                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                                        <Button
                                            onClick={() => handleDecrease(item.id)}
                                            variant="outlined"
                                            size="small"
                                            sx={{ marginRight: 1 }}
                                        >
                                            <RemoveIcon />
                                        </Button>

                                        <Typography variant="body1">{item.qty}</Typography>

                                        <Button
                                            onClick={() => handleIncrease(item)}
                                            variant="outlined"
                                            size="small"
                                            sx={{ marginLeft: 1 }}
                                        >
                                            <AddIcon />
                                        </Button>
                                    </Box>
                                </CardContent>
                            </Card>
                        </Grid>
                    ))}
                </Grid>
            )}

            {cartItems.length > 0 && (
                <Box sx={{ display: 'flex', justifyContent: 'flex-end', marginTop: 2 }}>
                    <Typography variant="h6">
                        Total: ${totalPrice.toFixed(2)}
                    </Typography>
                </Box>
            )}
        </Box>
    );
};

export default CartPage;
