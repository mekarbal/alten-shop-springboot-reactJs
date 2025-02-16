interface CartItem {
    id: string;
    title: string;
    price: number;
    qty: number;
    image: string;
}

interface CartState {
    cartItems: CartItem[];
    totalItems: number;
}