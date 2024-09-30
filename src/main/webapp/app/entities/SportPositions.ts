import { Competitors } from './Competitors';
import { Sports } from './Sports';
export interface SportPositions {
    sportId: number;
    sportPositionCode: string;
    description?: number;
    sports?: Sports;
    competitors?: Competitors[];
}
