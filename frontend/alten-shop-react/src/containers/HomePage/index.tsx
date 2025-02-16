import { useGetProductsQuery } from "@/core/store/features/products/apis";
import { Container, CircularProgress, Box } from "@mui/material";
import Products from "@/containers/HomePage/components/Products";

export const HomePage = () => {
    const { data, isLoading, isError, error } = useGetProductsQuery();

    if (isLoading) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: '100vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    if (isError) {
        return <div>Error: {error.message}</div>;
    }

    return (
        <Container>
            <h1>Home Page</h1>
            <Products data={data} />
        </Container>
    );
};

