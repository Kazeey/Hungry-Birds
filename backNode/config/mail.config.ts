const nodemailer = require ('nodemailer');

export const transporter = nodemailer.createTransport({
    service : 'Yahoo',
    auth : {
        user : 'projethungrybirds@yahoo.com',
        pass : 'bfichuctjogzgdjz'
    }
});