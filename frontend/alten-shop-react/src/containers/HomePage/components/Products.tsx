import {Product} from "@/core/store/features/products/types";
import {Grid, Card, CardContent, CardMedia, Typography, Box, Button} from "@mui/material";
import {addToCart} from "@/core/features/cart/cartSlice";
import {useDispatch} from "react-redux";

const Products = ({data}: { data: Product[] }) => {
    const dispatch = useDispatch();

    const handleAddToCart = (product) => {
        dispatch(addToCart(product));
    }



    return (<Grid container spacing={3}>
            {data.map((product: Product) => (<Grid item xs={12} sm={6} md={3} key={product.id}>
                    <Card sx={{maxWidth: "100%"}}>
                        <CardMedia
                            component="img"
                            height="140"
                            image={product.image}
                            alt={product.title}
                            sx={{objectFit: "contain"}}
                        />
                        <CardContent>
                            <Typography gutterBottom variant="h6" component="div" sx={{fontSize: '0.875rem', height: 100, overflow: 'hidden'}}>
                                {product.title}
                            </Typography>
                            <Typography variant="body2" color="text.secondary"
                            sx={{fontSize: '0.875rem', height: 60, overflow: 'hidden'}}
                            >
                                {product.description}
                            </Typography>
                            <Typography variant="h6" color="primary" sx={{marginTop: 1}}>
                                ${product.price}
                            </Typography>
                        </CardContent>
                        <Button
                            variant="contained"
                            color="primary"
                            fullWidth
                            sx={{
                                marginTop: 1,
                                padding: '10px 0',
                                fontWeight: 600,
                                backgroundColor: '#0073e6',
                                '&:hover': {
                                    backgroundColor: '#005bb5',
                                },
                            }}
                            onClick={()=>handleAddToCart(product)}
                        >
                            Add to Cart
                        </Button>
                    </Card>
                </Grid>))}
        </Grid>);
};

export default Products;
