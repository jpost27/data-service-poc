import { Competitors } from './Competitors';
export interface CompetitorStatuses {
    competitorStatusCode: string;
    description?: string;
    competitors?: Competitors[];
}
