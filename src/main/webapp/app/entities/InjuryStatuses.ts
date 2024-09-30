import { Competitors } from './Competitors';
export interface InjuryStatuses {
    injuryStatusCode: string;
    description?: string;
    competitors?: Competitors[];
}
