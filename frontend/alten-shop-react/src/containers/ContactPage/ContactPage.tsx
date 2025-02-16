import React, { useState } from "react";
import {
    Box,
    Button,
    TextField,
    Typography,
    Alert,
    Container,
    Paper,
} from "@mui/material";
import { useForm, Controller } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import {contactSchema} from "@/containers/ContactPage/validation";
import {ContactFormData} from "@/containers/ContactPage/types";

export const ContactPage: React.FC = () => {
    const {
        handleSubmit,
        control,
        formState: { errors },
        reset,
    } = useForm<ContactFormData>({
        resolver: yupResolver(contactSchema),
    });

    const [successMessage, setSuccessMessage] = useState<string | null>(null);

    const onSubmit = (data: ContactFormData) => {
        setSuccessMessage("Demande de contact envoyée avec succès.");
        reset();
    };

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{ p: 4, mt: 5, borderRadius: 2 }}>
                <Typography variant="h4" textAlign="center" gutterBottom>
                    Contactez-nous
                </Typography>

                {successMessage && (
                    <Alert severity="success" sx={{ mb: 2 }}>
                        {successMessage}
                    </Alert>
                )}

                <form onSubmit={handleSubmit(onSubmit)} noValidate>
                    <Controller
                        name="email"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <TextField
                                {...field}
                                label="Email"
                                fullWidth
                                margin="normal"
                                error={!!errors.email}
                                helperText={errors.email?.message}
                            />
                        )}
                    />

                    <Controller
                        name="message"
                        control={control}
                        defaultValue=""
                        render={({ field }) => (
                            <TextField
                                {...field}
                                label="Message"
                                fullWidth
                                multiline
                                rows={4}
                                margin="normal"
                                error={!!errors.message}
                                helperText={errors.message?.message}
                            />
                        )}
                    />

                    <Box textAlign="center" mt={2}>
                        <Button type="submit" variant="contained" color="primary">
                            Envoyer
                        </Button>
                    </Box>
                </form>
            </Paper>
        </Container>
    );
};

