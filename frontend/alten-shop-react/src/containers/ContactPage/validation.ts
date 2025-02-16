import * as yup from "yup";

export const contactSchema = yup.object().shape({
    email: yup
        .string()
        .email("L'email n'est pas valide")
        .required("L'email est Obligatoire"),
    message: yup
        .string()
        .required("Le message est Obligatoire")
        .max(300, "Le message ne doit pas dépasser 300 caractères"),
});