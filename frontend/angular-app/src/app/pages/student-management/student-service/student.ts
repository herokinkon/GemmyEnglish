import { Classes } from '../../class-management/class-service/classes-model';

export interface Student {
    id: number;
    fullName: string;
    birthday: Date;
    email: string;
    facebook: string;
    contactNumber: string;
    occupation: string;
    parentContactNumber: string;
    parentEmail: string;
    attendance: boolean;
    classes: Classes[];
}
